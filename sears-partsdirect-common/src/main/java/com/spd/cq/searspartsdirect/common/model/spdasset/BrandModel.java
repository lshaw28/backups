package com.spd.cq.searspartsdirect.common.model.spdasset;

public class BrandModel {

	private String trueName;
	private String title;
	private String description;
	private String logoPath;
	private String path;
	private String brandLogo;

	public BrandModel(String trueName, String path, String title, String description, String logoPath, String brandLogo) {
		this.trueName = trueName;
		this.title = title;
		this.description = description;
		this.logoPath = logoPath;
		this.path = path;
		this.brandLogo = brandLogo;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((logoPath == null) ? 0 : logoPath.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((brandLogo == null) ? 0 : brandLogo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof BrandModel)) {
			return false;
		}
		BrandModel other = (BrandModel) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (logoPath == null) {
			if (other.logoPath != null) {
				return false;
			}
		} else if (!logoPath.equals(other.logoPath)) {
			return false;
		}
		if (path == null) {
			if (other.path != null) {
				return false;
			}
		} else if (!path.equals(other.path)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (brandLogo == null) {
			if (other.brandLogo != null) {
				return false;
			}
		} else if (!brandLogo.equals(other.brandLogo)) {
			return false;
		}
		return true;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}
}
