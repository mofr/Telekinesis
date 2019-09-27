package org.telekinesis.client;

public class Window {
    public String id;
    public String title;
    public String iconLink;
    public String processId;
    public String processName;

    @Override
    public String toString() {
        return "org.org.telekinesis.client.Window{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", iconLink='" + iconLink + '\'' +
                ", processId='" + processId + '\'' +
                ", processName='" + processName + '\'' +
                '}';
    }
}
