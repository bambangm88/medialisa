package com.rsah.koperasi.Model.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCollection {

    @SerializedName("status")
    private String status = null;

    @SerializedName("total")
    private String total = null;

    @SerializedName("data")
    private List<data> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ResponseCollection.data> getData() {
        return data;
    }

    public void setData(List<ResponseCollection.data> data) {
        this.data = data;
    }






    public class data {

        @SerializedName("id_collection")
        private String id_collection;

        @SerializedName("caption")
        private String caption;

        @SerializedName("waktu_crawl")
        private String waktu_crawl;

        @SerializedName("data_komentar")
        private String data_komentar;

        @SerializedName("hasil_emotion")
        private String hasil_emotion;

        @SerializedName("hastag")
        private String hastag;

        @SerializedName("isi_url")
        private String isi_url;

        @SerializedName("post_url")
        private String post_url;

        @SerializedName("post_client")
        private String post_client;

        @SerializedName("num_comment")
        private String num_comment;

        @SerializedName("num_share")
        private String num_share;

        @SerializedName("num_like")
        private String num_like;

        @SerializedName("nama_akun")
        private String nama_akun;



        @SerializedName("verified")
        private String verified;

        @SerializedName("tanggal_posting")
        private String tanggal_posting;

        @SerializedName("update_at")
        private String update_at;

        @SerializedName("num_dislike")
        private String num_dislike;

        @SerializedName("location")
        private String location;


        public String getId_collection() {
            return id_collection;
        }

        public void setId_collection(String id_collection) {
            this.id_collection = id_collection;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getWaktu_crawl() {
            return waktu_crawl;
        }

        public void setWaktu_crawl(String waktu_crawl) {
            this.waktu_crawl = waktu_crawl;
        }

        public String getData_komentar() {
            return data_komentar;
        }

        public void setData_komentar(String data_komentar) {
            this.data_komentar = data_komentar;
        }

        public String getHasil_emotion() {
            return hasil_emotion;
        }

        public void setHasil_emotion(String hasil_emotion) {
            this.hasil_emotion = hasil_emotion;
        }

        public String getHastag() {
            return hastag;
        }

        public void setHastag(String hastag) {
            this.hastag = hastag;
        }

        public String getIsi_url() {
            return isi_url;
        }

        public void setIsi_url(String isi_url) {
            this.isi_url = isi_url;
        }

        public String getPost_url() {
            return post_url;
        }

        public void setPost_url(String post_url) {
            this.post_url = post_url;
        }

        public String getPost_client() {
            return post_client;
        }

        public void setPost_client(String post_client) {
            this.post_client = post_client;
        }

        public String getNum_comment() {
            return num_comment;
        }

        public void setNum_comment(String num_comment) {
            this.num_comment = num_comment;
        }

        public String getNum_share() {
            return num_share;
        }

        public void setNum_share(String num_share) {
            this.num_share = num_share;
        }

        public String getNum_like() {
            return num_like;
        }

        public void setNum_like(String num_like) {
            this.num_like = num_like;
        }

        public String getNama_akun() {
            return nama_akun;
        }

        public void setNama_akun(String nama_akun) {
            this.nama_akun = nama_akun;
        }

        public String getVerified() {
            return verified;
        }

        public void setVerified(String verified) {
            this.verified = verified;
        }

        public String getTanggal_posting() {
            return tanggal_posting;
        }

        public void setTanggal_posting(String tanggal_posting) {
            this.tanggal_posting = tanggal_posting;
        }

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }

        public String getNum_dislike() {
            return num_dislike;
        }

        public void setNum_dislike(String num_dislike) {
            this.num_dislike = num_dislike;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }




    }


    public static class komentar {


        @SerializedName("username")
        private String username;


        @SerializedName("name_card")
        private String name_card;


        @SerializedName("emotion")
        private String emotion;


        @SerializedName("verify")
        private String verify;


        @SerializedName("tweet")
        private String tweet;


        @SerializedName("gender")
        private String gender;


        @SerializedName("religi")
        private String religi;


        @SerializedName("link_stat")
        private String link_stat;


        @SerializedName("datetimes")
        private String datetimes;


        @SerializedName("replies")
        private String replies;


        @SerializedName("retweets")
        private String retweets;


        @SerializedName("likes")
        private String likes;


        @SerializedName("source")
        private String source;


        @SerializedName("deskripsi")
        private String deskripsi;


        @SerializedName("url_external_short")
        private String url_external_short;


        @SerializedName("url_external_text")
        private String url_external_text;

        @SerializedName("location")
        private String location;


        @SerializedName("user_create")
        private String user_create;


        @SerializedName("following")
        private String following;

        @SerializedName("followers")
        private String followers;


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName_card() {
            return name_card;
        }

        public void setName_card(String name_card) {
            this.name_card = name_card;
        }

        public String getEmotion() {
            return emotion;
        }

        public void setEmotion(String emotion) {
            this.emotion = emotion;
        }

        public String getVerify() {
            return verify;
        }

        public void setVerify(String verify) {
            this.verify = verify;
        }

        public String getTweet() {
            return tweet;
        }

        public void setTweet(String tweet) {
            this.tweet = tweet;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getReligi() {
            return religi;
        }

        public void setReligi(String religi) {
            this.religi = religi;
        }

        public String getLink_stat() {
            return link_stat;
        }

        public void setLink_stat(String link_stat) {
            this.link_stat = link_stat;
        }

        public String getDatetimes() {
            return datetimes;
        }

        public void setDatetimes(String datetimes) {
            this.datetimes = datetimes;
        }

        public String getReplies() {
            return replies;
        }

        public void setReplies(String replies) {
            this.replies = replies;
        }

        public String getRetweets() {
            return retweets;
        }

        public void setRetweets(String retweets) {
            this.retweets = retweets;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        public String getUrl_external_short() {
            return url_external_short;
        }

        public void setUrl_external_short(String url_external_short) {
            this.url_external_short = url_external_short;
        }

        public String getUrl_external_text() {
            return url_external_text;
        }

        public void setUrl_external_text(String url_external_text) {
            this.url_external_text = url_external_text;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getUser_create() {
            return user_create;
        }

        public void setUser_create(String user_create) {
            this.user_create = user_create;
        }

        public String getFollowing() {
            return following;
        }

        public void setFollowing(String following) {
            this.following = following;
        }

        public String getFollowers() {
            return followers;
        }

        public void setFollowers(String followers) {
            this.followers = followers;
        }







    }

}
