<?php 

class Input{
    static function get($key){
        return isset($_GET[$key]) ? $_GET[$key] : null;
    }

    static function post($key){
        return isset($_POST[$key]) ? $_POST[$key] : null;
    }
}