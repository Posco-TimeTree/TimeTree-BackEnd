package pack.springjpa1.common.domain;

public class CanvasStateRequest {
    private String canvasState;
    private int userId;



    public String getCanvasState() {
        return canvasState;
    }

    public void setCanvasState(String canvasState) {
        this.canvasState = canvasState;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
