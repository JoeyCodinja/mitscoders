package mits.uwi.com.ourmobileenvironment.ourvle.classes.models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class ModuleContent extends SugarRecord<ModuleContent> {

	@SerializedName("type")
	String type;

	@SerializedName("filename")
	String filename;

	@SerializedName("filepath")
	String filepath;

	@SerializedName("filesize")
	int filesize;

	@SerializedName("fileurl")
	String fileurl;

	@SerializedName("content")
	String content;

	@SerializedName("timecreated")
	int timecreated;

	@SerializedName("timemodified")
	int timemodified;

	@SerializedName("sortorder")
	int sortorder;

	@SerializedName("userid")
	int userid;

	@SerializedName("author")
	String author;

	@SerializedName("license")
	String license;

	// Relational parameters
	Long parentid;
	int moduleid;
	int sectionid;
	int courseid;

	/**
	 * Get content type
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Get filename as saved in Moodle
	 * 
	 * @return
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Get filepath
	 * 
	 * @return
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * Get filesize
	 * 
	 * @return
	 */
	public int getFilesize() {
		return filesize;
	}

	/**
	 * Get file download url. <br/>
	 * <b>Note:</b> token must be appended as a param to download
	 * 
	 * @return
	 */
	public String getFileurl() {
		return fileurl;
	}

	public String getContent() {
		return content;
	}

	public int getTimecreated() {
		return timecreated;
	}

	public int getTimemodified() {
		return timemodified;
	}

	public int getSortorder() {
		return sortorder;
	}

	/**
	 * Get author userid
	 * @return
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * Get author name
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	public String getLicense() {
		return license;
	}

	/**
	 * Get the database id of the parent module. Not to be confused with actual
	 * moduleid given to a module by Moodle site. This id is given by Sugar db
	 * while saving the parent module
	 * 
	 * @return
	 */
	public Long getParentid() {
		return parentid;
	}

	/**
	 * moduleid of the module to which this module content belongs to. This id
	 * is given to a module by Moodle site.
	 * 
	 * @return
	 */
	public int getModuleid() {
		return moduleid;
	}

	/**
	 * sectionid of the section to which this module content content belongs to.
	 * This id is given to a section by Moodle site.
	 * 
	 * @return
	 */
	public int getSectionid() {
		return sectionid;
	}

	/**
	 * courseid of the course to which this section belongs to. This id is given
	 * to a course by Moodle site
	 * 
	 * @return
	 */
	public int getCourseid() {
		return courseid;
	}

	/**
	 * Set the content parent module db id
	 * 
	 * @param parentid
	 */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	/**
	 * Set the content course Moodle id
	 * 
	 * @param courseid
	 */
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	/**
	 * Set the content parent module section Moodle id
	 * 
	 * @param sectionid
	 */
	public void setSectionid(int sectionid) {
		this.sectionid = sectionid;
	}

	/**
	 * Set the content parent module Moodle id
	 * 
	 * @param moduleid
	 */
	public void setModuleid(int moduleid) {
		this.moduleid = moduleid;
	}


}
