package pojoClass;

public class MainJson {
	private PostMain posts;
	public PostMain getPosts() {
		return posts;
	}
	public void setPosts(PostMain posts) {
		this.posts = posts;
	}
	public comments getComments() {
		return comments;
	}
	public void setComments(comments comments) {
		this.comments = comments;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	private comments comments;
	private Profile profile;

}
