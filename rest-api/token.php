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
    case 'POST':
        try {
            $token = Input::post('token');            

            $sql = 'SELECT * FROM token WHERE token_key = ?';

           
            $prep = $db->prepare($sql);
            $prep->execute([
                $token
            ]);
            if($prep->rowCount() === 0){
                $sql = 'INSERT INTO token(token_key) VALUES (?)';

                $prep = $db->prepare($sql);
                $prep->execute([
                    $token
                ]);

                echo json_encode([
                    'status' => 'success',
                    'data'   => 'Token berhasil ditambahkan'
                ]);
            }else{
                echo json_encode([
                    'status' => 'fail',
                    'data'   => 'Token sudah ada'
                ]);
            }
        } catch (Exception | PDOException $e) {
            echo json_encode([
                'status'  => 'fail',
                'message' => 'DB Fail : ' . $e->getMessage()
            ]);
        }
    break;
}