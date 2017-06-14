package io.dataBaseInteraction;

/**
 * Created by danil on 13.06.2017.
 */
public interface DataBaseInteraction {
    public final int INIT_TABLE=0;
    public final int CLEAR_TABLE=1;
    public final int CHANGE_ELEMENT=2;
    public final int ADD_ELEMENT=3;
    public final int REMOVE_ELEMENT=4;
    public final int REFRESH_TABLE=5;
    public final byte[] IP = new byte[]{(byte) 192, (byte)168, 1, (byte)129};

}
