package livelihoodzone.entity.questionnaire.tribe;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "ethnic_groups")
public class EthnicGroupsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EthnicGroupId")
    private int ethnicGroupId;

    @Column(name = "EthnicGroupName")
    private String ethnicGroupName;

    @Column(name = "EthnicGroupCode")
    private int ethnicGroupCode;

    public EthnicGroupsEntity() {
    }

    public EthnicGroupsEntity(String ethnicGroupName, int ethnicGroupCode) {
        this.ethnicGroupName = ethnicGroupName;
        this.ethnicGroupCode = ethnicGroupCode;
    }

    public int getEthnicGroupId() {
        return ethnicGroupId;
    }

    public void setEthnicGroupId(int ethnicGroupId) {
        this.ethnicGroupId = ethnicGroupId;
    }

    public String getEthnicGroupName() {
        return ethnicGroupName;
    }

    public void setEthnicGroupName(String ethnicGroupName) {
        this.ethnicGroupName = ethnicGroupName;
    }

    public int getEthnicGroupCode() {
        return ethnicGroupCode;
    }

    public void setEthnicGroupCode(int ethnicGroupCode) {
        this.ethnicGroupCode = ethnicGroupCode;
    }
}
