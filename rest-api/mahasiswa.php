<?php

define('SERVER', 'localhost:3306');
define('DATABASE', 'mahasiswa-android');
define('USERNAME', 'root');
define('PASSWORD', 'root');

header('Content-Type: application/json');

try{
    $db = new PDO('mysql:host='.SERVER.';dbname='.DATABASE, USERNAME, PASSWORD);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}catch(PDOException | Exception $e){
    echo json_encode([
        'status'  => 'fail',
        'message' => 'DB Fail : '.$e->getMessage()
    ]);
    die();
}

require_once 'Input.php';

$method = isset($_SERVER['REQUEST_METHOD']) ? strtoupper($_SERVER['REQUEST_METHOD']) : null;
// $input = json_decode($input);
switch($method){
    case 'GET':
        try{
            $nim = Input::get('nim');
            
            $sql = 'SELECT * FROM mahasiswa';
            
            if (!empty($nim)) {
                $sql .= ' WHERE nim = ?';
                $prep = $db->prepare($sql);
                $prep->execute([
                    $nim
                ]);
                $data = $prep->fetch(PDO::FETCH_OBJ);

                if($data === false){
                    throw new Exception('Data Not Found');
                }
            }else{
                $prep = $db->prepare($sql);
                $prep->execute();
                $data = $prep->fetchAll(PDO::FETCH_OBJ);
            }

            

            echo json_encode([
                'status' => 'success',
                'data'   => $data
            ]);
        }catch(Exception | PDOException $e){
            echo json_encode([
                'status'  => 'fail',
                'message' => 'DB Fail : ' . $e->getMessage()
            ]);
        }
    break;

    case 'POST':
        try {
            $nim = Input::post('nim');
            $nama = Input::post('nama');
            $program_studi = Input::post('program_studi');
            $alamat = Input::post('alamat');

            $sql = '
                INSERT INTO 
                    mahasiswa(nim, nama, program_studi, alamat)
                VALUES (?, ?, ?, ?)
            ';

            
            $prep = $db->prepare($sql);
            
            $prep->execute([
                $nim, $nama, $program_studi, $alamat
            ]);

            if($prep->rowCount()){
                $message = 'Data berhasil dimasukkan';
            }else{
                $message = 'Data gagal dimasukkan';
            }

            $sql_token = '
                SELECT * FROM token
            ';


            $prep_token = $db->prepare($sql_token);

            $prep_token->execute([
                $nim, $nama, $program_studi, $alamat
            ]);

            $tokens = [];

            foreach($prep_token->fetchAll(PDO::FETCH_OBJ) as $data){
                array_push($tokens, $data->token_key);
            }

            $json_data = [
                "registration_ids" => $tokens,
                "notification" => [
                    "body" => "Berhasil!",
                    "title" => "Data ".$nama." berhasil ditambahkan",
                    // "icon" => "ic_launcher"
                ],
                // "data" => [
                //     "ANYTHING EXTRA HERE"
                // ]
            ];

            $data = json_encode($json_data);
            //FCM API end-point
            $url = 'https://fcm.googleapis.com/fcm/send';
            //api_key in Firebase Console -> Project Settings -> CLOUD MESSAGING -> Server key
            $server_key = '';
            //header with content_type api key
            $headers = array(
                'Content-Type:application/json',
                'Authorization:key=' . $server_key
            );
            //CURL request to route notification to FCM connection server (provided by Google)
            $ch = curl_init();
            curl_setopt($ch, CURLOPT_URL, $url);
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
            curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
            curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
            $result = curl_exec($ch);

            if ($result === FALSE) {
                die('Oops! FCM Send Error: ' . curl_error($ch));
            }
            curl_close($ch);
            
            echo json_encode([
                'status'  => 'success',
                'message' => $message
            ]);
        } catch (Exception | PDOException $e) {
            echo json_encode([
                'status'  => 'fail',
                'message' => 'DB Fail : ' . $e->getMessage()
            ]);
        }
    break;

    case 'PUT':
        try {
            $nim = Input::post('nim');
            $nama = Input::post('nama');
            $program_studi = Input::post('program_studi');
            $alamat = Input::post('alamat');

            $sql = '
                UPDATE
                    mahasiswa
                SET nama = ?, program_studi = ?, alamat = ?
                WHERE nim = ?
            ';

            $prep = $db->prepare($sql);

            $prep->execute([
                $nama, $program_studi, $alamat, $nim
            ]);

            if ($prep->rowCount()) {
                $message = 'Data berhasil diubah';
            } else {
                $message = 'Data gagal diubah';
            }

            echo json_encode([
                'status'  => 'success',
                'message' => $message
            ]);
        } catch (Exception | PDOException $e) {
            echo json_encode([
                'status'  => 'fail',
                'message' => 'DB Fail : ' . $e->getMessage()
            ]);
        }
    break;

    case 'DELETE':
        try {
            $nim = Input::post('nim');

            $sql = '
                DELETE FROM mahasiswa
                WHERE nim = ?
            ';

            $prep = $db->prepare($sql);

            $prep->execute([
                $nim
            ]);

            if ($prep->rowCount()) {
                $message = 'Data berhasil dihapus';
            } else {
                $message = 'Data gagal dihapus';
            }

            echo json_encode([
                'status'  => 'success',
                'message' => $message
            ]);
        } catch (Exception | PDOException $e) {
            echo json_encode([
                'status'  => 'fail',
                'message' => 'DB Fail : ' . $e->getMessage()
            ]);
        }
    break;

    default: 
        die('Not Found');
    break;
}
