package in.dragonbra.klayb0t.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Deprecated
public class TwitchCheckUserSubResponse {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("sub_plan")
    @Expose
    private String subPlan;

    @SerializedName("sub_plan_name")
    @Expose
    private String subPlanName;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubPlan() {
        return subPlan;
    }

    public void setSubPlan(String subPlan) {
        this.subPlan = subPlan;
    }

    public String getSubPlanName() {
        return subPlanName;
    }

    public void setSubPlanName(String subPlanName) {
        this.subPlanName = subPlanName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}