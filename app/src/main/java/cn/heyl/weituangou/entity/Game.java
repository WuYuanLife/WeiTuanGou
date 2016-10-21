package cn.heyl.weituangou.entity;

/**
 * Creaded by heyl 2016-10-11
 */
public class Game {
	private String name;
	private String url;

	public Game(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public Game() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Game [" + (name != null ? "name=" + name + ", " : "")
				+ (url != null ? "url=" + url : "") + "]";
	}

}
