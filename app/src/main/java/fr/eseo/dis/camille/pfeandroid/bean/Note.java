
package fr.eseo.dis.camille.pfeandroid.bean;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userId",
    "forename",
    "surname",
    "mynote",
    "avgNote"
})
public class Note {

    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("forename")
    private String forename;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("mynote")
    private Integer mynote;
    @JsonProperty("avgNote")
    private Object avgNote;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("forename")
    public String getForename() {
        return forename;
    }

    @JsonProperty("forename")
    public void setForename(String forename) {
        this.forename = forename;
    }

    @JsonProperty("surname")
    public String getSurname() {
        return surname;
    }

    @JsonProperty("surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonProperty("mynote")
    public Integer getMynote() {
        return mynote;
    }

    @JsonProperty("mynote")
    public void setMynote(Integer mynote) {
        this.mynote = mynote;
    }

    @JsonProperty("avgNote")
    public Object getAvgNote() {
        return avgNote;
    }

    @JsonProperty("avgNote")
    public void setAvgNote(Object avgNote) {
        this.avgNote = avgNote;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
