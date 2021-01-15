<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Bengkel_api extends CI_Controller {

	public function __construct() {
        parent::__construct();
        $this->load->helper('url');
        $this->load->model('Bengkel_model');
				$this->load->library('encryption');
     }
	public function index()
	{
		$data = $this->Bengkel_model->get_Databenkel();

		print_r(json_encode($data));
	}

	function getDetailBengkel($id = null){

		$data = $this->Bengkel_model->get_GambarBengkel($id);

		// $row[] = $data;
		// $detail = array('detailbengkel'=>$data);
		// echo "<pre>";
			// print_r(json_encode($detail));
			print_r(json_encode($data,JSON_UNESCAPED_SLASHES));
		// echo "</pre>";

	}

	function listbengkel(){
		$url = base_url('assets/image_bengkel/bengkel_1.jpeg');
		$data = array(

			'image_title' => 'Bengkel One Service',
			'image_url'	  => 'http://192.168.1.9/bengkel_gis_api/assets/image_bengkel/bengkel_1.jpeg'

		);
		$row[] = $data;

		print_r(json_encode($row,JSON_UNESCAPED_SLASHES));
	}

	function JSON($data) {
		header('Content-Type: application/json');
		echo json_encode($data);
	}

	public function AddData () {
		$json = json_decode(file_get_contents('php://input'),true);

	$image 		= $json["image"];
	$name 		= $json["name"];
	$fullname 	= $json["fullname"];
	$email 		= $json["email"];
    $address 	= $json["address"];
    $longitude	= $json["longitude"];
    $latitude	= $json["latitude"];
    $mobile 	= $json["mobile"];

	$openday 	= $json["openday"];
    $start 		= $json["start"];
    $close 		= $json["close"];
    

	  $object = array ('avatar' => $name.".jpg");

   	$arrpos = array(
   		'nama' 		=> $fullname,
   		'telp' 		=> $mobile,
   		'email' 	=> $email,
		'alamat' 	=> $address,
		'hari'  	=> $openday,
		'jam_buka'	=> $start,
		'jam_tutup'	=> $close,
		'gambar'	=> "http://10.0.2.2/bengkel_gis_api/assets/image_bengkel/".$name.".jpg",
		'longitude'	=> $longitude,
		'latitude'	=> $latitude

   	);

	  $this->db->insert('tbl_bengkel', $arrpos);

	  $decodedImage = base64_decode("$image");
	  $return = file_put_contents("./assets/image_bengkel/".$name.".jpg", $decodedImage);

	 	$response = array();
	    if($return !== false){
	        $response['success'] = 1;
	        $response['message'] = "Success....";
	        $response['avatar'] = $name.".jpg";
	    }else{
	        $response['success'] = 0;
	        $response['message'] = "Failed ....";
	    }

	    $this->JSON($response);
	}
}
