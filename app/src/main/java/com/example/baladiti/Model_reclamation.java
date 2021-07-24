package com.example.baladiti;

public class Model_reclamation {
    private String title;
    private String desc;
    private String lib_ar;
    private String created_at;

    public Model_reclamation() {
    }

    public Model_reclamation(String title, String desc, String lib_ar, String created_at) {
        this.title = title;
        this.desc = desc;
        //this.lib_ar = lib_ar;
      //  this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

   // public String getLib_ar() {
     //   return lib_ar;
   // }

   // public String getCreated_at() {
       // return created_at;
    //}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //public void setLib_ar(String lib_ar) {
       // this.lib_ar = lib_ar;
   // }

  //  public void setCreated_at(String created_at) {
       // this.created_at = created_at;
   // }
}
