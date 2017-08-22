package ron.gfycat.model;

/**
 * Created by RoN on 8/21/2017.
 */

public class Album {

    private String id;
    private String description;
    private String folderSubType;
    private String title;
    private String [] tags;
    private String linkText;
    private int published;
    private Album [] nodes;
    private String path;

    //
    private String coverImageUrl;
    private String coverImageUrl_mobile;
    private int width;
    private int  height;
    private String mp4Url;
    private String webmUrl;
    private String webpUrl;
    private String mobileUrl;
    private String mobilePosterUrl;
    private String posterUrl;
    private String thumb360Url;
    private String thumb360PosterUrl;
    private String thumb100PosterUrl;
    private String max5mbGif;
    private String max2mbGif;
    private String miniUrl;
    private String miniPosterUrl;
    private String mjpgUrl;
    private String gifUrl;


    public Album(String id,String description,String folderSubType,String title,String [] tags, String linkText,int published,String path/*,Album [] nodes*/){
        this.id=id;
        this.description=description;
        this.folderSubType=folderSubType;
        this.title=title;
        this.tags=tags;
        this.linkText=linkText;
        this.published=published;
        //this.nodes=nodes;
        this.path=path;
    }

    public Album(String id,String title){
        this.id=id;
        this.title=title;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFolderSubType() {
        return folderSubType;
    }

    public void setFolderSubType(String folderSubType) {
        this.folderSubType = folderSubType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTags() {
        return tags;
    }
    public String getTagsString() {
        if(this.tags!=null && this.tags.length>0)
            return tags[0]+(this.tags.length>1?(","+tags[0]):"")+(this.tags.length>2?(","+tags[2]):"");
        else
            return "";
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public Album[] getNodes() {
        return nodes;
    }

    public void setNodes(Album[] nodes) {
        this.nodes = nodes;
    }

    public String getCoverImageUrl_mobile() {
        return coverImageUrl_mobile;
    }

    public void setCoverImageUrl_mobile(String coverImageUrl_mobile) {
        this.coverImageUrl_mobile = coverImageUrl_mobile;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMp4Url() {
        return mp4Url;
    }

    public void setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url;
    }

    public String getWebmUrl() {
        return webmUrl;
    }

    public void setWebmUrl(String webmUrl) {
        this.webmUrl = webmUrl;
    }

    public String getWebpUrl() {
        return webpUrl;
    }

    public void setWebpUrl(String webpUrl) {
        this.webpUrl = webpUrl;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getMobilePosterUrl() {
        return mobilePosterUrl;
    }

    public void setMobilePosterUrl(String mobilePosterUrl) {
        this.mobilePosterUrl = mobilePosterUrl;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getThumb360Url() {
        return thumb360Url;
    }

    public void setThumb360Url(String thumb360Url) {
        this.thumb360Url = thumb360Url;
    }

    public String getThumb360PosterUrl() {
        return thumb360PosterUrl;
    }

    public void setThumb360PosterUrl(String thumb360PosterUrl) {
        this.thumb360PosterUrl = thumb360PosterUrl;
    }

    public String getThumb100PosterUrl() {
        return thumb100PosterUrl;
    }

    public void setThumb100PosterUrl(String thumb100PosterUrl) {
        this.thumb100PosterUrl = thumb100PosterUrl;
    }

    public String getMax5mbGif() {
        return max5mbGif;
    }

    public void setMax5mbGif(String max5mbGif) {
        this.max5mbGif = max5mbGif;
    }

    public String getMax2mbGif() {
        return max2mbGif;
    }

    public void setMax2mbGif(String max2mbGif) {
        this.max2mbGif = max2mbGif;
    }

    public String getMiniUrl() {
        return miniUrl;
    }

    public void setMiniUrl(String miniUrl) {
        this.miniUrl = miniUrl;
    }

    public String getMiniPosterUrl() {
        return miniPosterUrl;
    }

    public void setMiniPosterUrl(String miniPosterUrl) {
        this.miniPosterUrl = miniPosterUrl;
    }

    public String getMjpgUrl() {
        return mjpgUrl;
    }

    public void setMjpgUrl(String mjpgUrl) {
        this.mjpgUrl = mjpgUrl;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    @Override
    public String toString() {
        return "id: "+this.id+",title: "+this.title+", folder: "+folderSubType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String toString2() {
        StringBuilder sb = new StringBuilder();

        return sb.append("{<br/>")
                .append("<strong>id: </strong>").append("\"").append(this.id).append("\"<br/>")
                .append("<strong>title:</strong> ").append("\"").append(this.title).append("\"<br/>")
                .append("<strong>description:</strong> ").append("\"").append(this.description).append("\"<br/>")
                .append("<strong>linkText:</strong> ").append("\"").append(this.linkText).append("\"<br/>")
                .append("<strong>tags:</strong> ").append("\"").append(this.getTagsString()).append("\"<br/>")
                .append("<strong>folderSubType:</strong> ").append("\"").append(this.folderSubType).append("\"<br/>")
                .append("<strong>published:</strong> ").append("\"").append(this.published).append("\"<br/>")
                .append("<strong>path:</strong> ").append("\"").append(this.path).append("\"<br/>")

                .append("<strong>coverImageUrl:</strong> ").append("\"").append(this.coverImageUrl).append("\"<br/>")
                .append("<strong>coverImageUrl-mobile:</strong> ").append("\"").append(this.coverImageUrl_mobile).append("\"<br/>")
                .append("<strong>width:</strong> ").append(this.width).append("<br/>")
                .append("<strong>height:</strong> ").append(this.height).append("<br/>")
                .append("<strong>mp4Url:</strong> ").append("\"").append(this.mp4Url).append("\"<br/>")
                .append("<strong>webmUrl:</strong> ").append("\"").append(this.webmUrl).append("\"<br/>")
                .append("<strong>webpUrl:</strong> ").append("\"").append(this.webpUrl).append("\"<br/>")
                .append("<strong>mobileUrl:</strong> ").append("\"").append(this.mobileUrl).append("\"<br/>")
                .append("<strong>mobilePosterUrl:</strong> ").append("\"").append(this.mobilePosterUrl).append("\"<br/>")
                .append("<strong>posterUrl:</strong> ").append("\"").append(this.posterUrl).append("\"<br/>")
                .append("<strong>thumb360Url:</strong> ").append("\"").append(this.thumb360Url).append("\"<br/>")
                .append("<strong>thumb360PosterUrl:</strong> ").append("\"").append(this.thumb360PosterUrl).append("\"<br/>")
                .append("<strong>thumb100PosterUrl:</strong> ").append("\"").append(this.thumb100PosterUrl).append("\"<br/>")
                .append("<strong>max5mbGif:</strong> ").append("\"").append(this.max5mbGif).append("\"<br/>")
                .append("<strong>max2mbGif:</strong> ").append("\"").append(this.max2mbGif).append("\"<br/>")
                .append("<strong>miniUrl:</strong> ").append("\"").append(this.miniUrl).append("\"<br/>")
                .append("<strong>miniPosterUrl:</strong> ").append("\"").append(this.miniPosterUrl).append("\"<br/>")
                .append("<strong>mjpgUrl:</strong> ").append("\"").append(this.mjpgUrl).append("\"<br/>")
                .append("<strong>gifUrl:</strong> ").append("\"").append(this.gifUrl).append("\"<br/>")


                .append("}")
                .toString();

    }
}
