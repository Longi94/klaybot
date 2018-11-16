package in.dragonbra.klayb0t.chat;

public abstract class BaseHandler {

    private long coolDown;

    private long lastHandle;

    public BaseHandler() {
        this(0L);
    }

    public BaseHandler(long coolDown) {
        this.coolDown = coolDown;
    }

    public long getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(long coolDown) {
        this.coolDown = coolDown;
    }

    public boolean canExecute(long currentTimestamp) {
        return lastHandle < currentTimestamp - coolDown;
    }

    public void setLastHandle(long lastHandle) {
        this.lastHandle = lastHandle;
    }
}
