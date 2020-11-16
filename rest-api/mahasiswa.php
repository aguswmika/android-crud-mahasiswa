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
