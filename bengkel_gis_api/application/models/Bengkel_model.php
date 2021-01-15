<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Bengkel_model extends CI_Model{

    public function __construct() {
        //parent::__construct();
        $this->load->database();
    }

    function get_Databenkel(){
        $this->db->select('*');
        $this->db->from('tbl_bengkel');
        $query = $this->db->get();
        return $query->result_array();
    }

    function get_DataDetailBengkel($id){
        $this->db->select('*');
        $this->db->from('tbl_bengkel');
        $this->db->where('id',$id);
        $query = $this->db->get();
        return $query->result_array();
    }

    function get_GambarBengkel($id){
        $this->db->select('*');
        $this->db->from('tbl_bengkel');
        $this->db->where('id',$id);
        $query = $this->db->get();
        return $query->result_array();
    }
}