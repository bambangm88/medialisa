package com.rsah.koperasi.Model.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCCServiceMonitoring {

    @SerializedName("status")
    private String status = null;

    @SerializedName("data_services_monitoring")
    private List<data_services_monitoring> data_services_monitoring = null;


    @SerializedName("data_log")
    private List<data_log> data_log = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResponseCCServiceMonitoring.data_services_monitoring> getData_services_monitoring() {
        return data_services_monitoring;
    }

    public void setData_services_monitoring(List<ResponseCCServiceMonitoring.data_services_monitoring> data_services_monitoring) {
        this.data_services_monitoring = data_services_monitoring;
    }

    public List<ResponseCCServiceMonitoring.data_log> getData_log() {
        return data_log;
    }

    public void setData_log(List<ResponseCCServiceMonitoring.data_log> data_log) {
        this.data_log = data_log;
    }



    public class data_services_monitoring {

        @SerializedName("id_service")
        private String id_service;

        @SerializedName("socmed_name")
        private String socmed_name;

        @SerializedName("percentage")
        private String percentage;

        @SerializedName("id_workspace")
        private String id_workspace;


        public String getId_service() {
            return id_service;
        }

        public void setId_service(String id_service) {
            this.id_service = id_service;
        }

        public String getSocmed_name() {
            return socmed_name;
        }

        public void setSocmed_name(String socmed_name) {
            this.socmed_name = socmed_name;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getId_workspace() {
            return id_workspace;
        }

        public void setId_workspace(String id_workspace) {
            this.id_workspace = id_workspace;
        }




    }

    public class data_log {

        @SerializedName("time")
        private String time;

        @SerializedName("socmed_id")
        private String socmed_id;

        @SerializedName("id_collection")
        private String id_collection;


        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSocmed_id() {
            return socmed_id;
        }

        public void setSocmed_id(String socmed_id) {
            this.socmed_id = socmed_id;
        }

        public String getId_collection() {
            return id_collection;
        }

        public void setId_collection(String id_collection) {
            this.id_collection = id_collection;
        }

    }

}
