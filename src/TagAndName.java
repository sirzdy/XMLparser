/**
 * Created by zdy on 2017/3/4.
 */
public class TagAndName implements Comparable<TagAndName>{
    private String tag;
    private String name;

    TagAndName(String tag,String name){
        this.tag=tag;
        this.name=name;
    }
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(TagAndName arg0) {
        return this.getTag().compareTo(arg0.getTag());
    }
}
