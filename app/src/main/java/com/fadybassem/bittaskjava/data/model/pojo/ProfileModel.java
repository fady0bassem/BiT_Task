package com.fadybassem.bittaskjava.data.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileModel {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public class Counts {

        @SerializedName("posts")
        @Expose
        private Integer posts;
        @SerializedName("followers")
        @Expose
        private Integer followers;
        @SerializedName("following")
        @Expose
        private Integer following;

        public Integer getPosts() {
            return posts;
        }

        public void setPosts(Integer posts) {
            this.posts = posts;
        }

        public Integer getFollowers() {
            return followers;
        }

        public void setFollowers(Integer followers) {
            this.followers = followers;
        }

        public Integer getFollowing() {
            return following;
        }

        public void setFollowing(Integer following) {
            this.following = following;
        }

    }

    public class Data {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("full_name")
        @Expose
        private String fullName;
        @SerializedName("profile_picture")
        @Expose
        private String profilePicture;
        @SerializedName("bio")
        @Expose
        private String bio;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("counts")
        @Expose
        private Counts counts;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Counts getCounts() {
            return counts;
        }

        public void setCounts(Counts counts) {
            this.counts = counts;
        }

    }
}
