package fr.eseo.dis.camille.pfeandroid.dto.juries;

/**
 * Created by Arthur on 20/12/2017.
 */

public class Info {

    private Member[] members;
    private ProjectInfo[] projects;

    public ProjectInfo[] getProjects() {
        return projects;
    }

    public void setProjects(ProjectInfo[] projects) {
        this.projects = projects;
    }

    public Member[] getMembers() {
        return members;
    }

    public void setMembers(Member[] members) {
        this.members = members;
    }
}
