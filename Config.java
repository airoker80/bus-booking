package com.domain.airoker80.testapplication;

/**
 * Created by airoker80 on 8/14/2016.
 */
public class Config {
    //public static final String URL_ADD="http://192.168.94.1/Android/CRUD/addEmp.php";
    //public static final String URL_GET_ALL = "http://192.168.94.1/Android/CRUD/getAllEmp.php";
    //public static final String URL_GET_EMP = "http://192.168.198.50/Test/readroute.php";
    public static final String URL_GET_EMP = Config.ip+"/Test/readroute.php";
    public static final String ip="http://192.168.123.3";
    public static final String url="http://192.168.123.3/Test/seatsinfo.php";
    public static final String sp_url="http://192.168.123.3/Test/sp_id.php";
    public static final String id_url="http://192.168.123.3/Test/route_id.php";
    private static final String BOOK_URL="http://192.168.123.3/Test/book1.php";
    //public static final String URL_UPDATE_EMP = "http://192.168.94.1/Android/CRUD/updateEmp.php";
    //public static final String URL_DELETE_EMP = "http://192.168.94.1/Android/CRUD/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_ID = "id";
    public static final String KEY_Route = "route";
    public static final String key_route="route";
    public static final String key_rand="rand";
    public static final String key_bus_no="bus_no";
    public static final String key_route_id="route_id";
    public static final String key_rem_seats="rem_seats";
    public static final String key_seats="no_of_seats";
    public static final String key_sp_id="sp_id";
    public static final String key_Password="password";
    public static String username = null;
    public static String No_of_seats = null;
    public static String date = null;
    public static String Time = null;
    public static String price = null;



    //public static final String KEY_EMP_DESG = "desg";
    //public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="books";
    public static final String TAG_ID = "id";
    public static final String TAG_Route = "route";
    public static final String TAG_Bus_id = "bus_id";
    public static final String TAG_sa = "seats_available";
    public static final String TAG_date = "date";
    public static final String TAG_time = "time";
    public static final String TAG_price = "price";
    public static final String TAG_username="username";
    public static final String TAG_nos="no_of_seats";
    //public static final String TAG_DESG = "desg";
    //public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
