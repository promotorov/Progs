package changes;

/**
 * Created by vladp on 17.05.2017.
 */
public interface TypesChanges {
    public final String CLEAR_ALL="CLEAR_ALL";
    public final String REMOVE="REMOVE";
    public final String EDIT="EDIT";
    public final String ADD="ADD";
    public final String REMOVEGR="REMOVEGR";

    public TypesChanges getElement();
    public void showElement();
    public String getType();
}
