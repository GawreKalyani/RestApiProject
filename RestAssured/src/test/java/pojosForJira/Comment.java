package pojosForJira;

import java.util.Date;

public class Comment {
	private String self;
	private String id;
	private Author author;
	private String body;
	private UpdateAuthor updateAuthor;
	private Date created;
	private Date updated;
	private Visibility visibility;
	
		public String getSelf() {
			return self;
		}
		public String getId() {
			return id;
		}
		public Author getAuthor() {
			return author;
		}
		public String getBody() {
			return body;
		}
		public UpdateAuthor getUpdateAuthor() {
			return updateAuthor;
		}
		public Date getCreated() {
			return created;
		}
		public Date getUpdated() {
			return updated;
		}
		public Visibility getVisibility() {
			return visibility;
		}
}
