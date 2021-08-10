package juc;


public class MyJob implements Comparable<MyJob> {

    private int id;

    public MyJob(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(MyJob job) {
        return this.id > job.id ? 1 : (this.id < job.id ? -1 : 0);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

}
