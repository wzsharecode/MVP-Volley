package lib.volley.http;

public interface INetResultInteract {

    void onRequestSuccess(int requestCode);

    void onRequestFaild(String errorNo, String errorMessage);

    void onNoConnect();
	
}
