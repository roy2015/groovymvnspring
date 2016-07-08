package v1

/**
 * Created by BG244210 on 2016/7/7.
 */
class Song {
    String firstname
    String lastname
    String toString() {
        "$firstname.$lastname"
    }

    Song(String firstname, String lastname) {
        this.firstname = firstname
        this.lastname = lastname
    }

    String getFirstname() {
        return firstname
    }

    void setFirstname(String firstname) {
        this.firstname = firstname
    }

    String getLastname() {
        return lastname
    }

    void setLastname(String lastname) {
        this.lastname = lastname
    }
}
