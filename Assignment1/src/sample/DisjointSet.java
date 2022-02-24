package sample;

public class DisjointSet<T> {
    public DisjointSet<?>
            parent = null;
    public T data;

    public DisjointSet(T data){
        this.data=data;
    }

    //Iterative version
    public static DisjointSet<?> find(DisjointSet<?> node){
        while(node.parent!=null) node=node.parent;
        return node;
    }



    public DisjointSet<?> getParent() {
        return parent;
    }

    public void setParent(DisjointSet<?> parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

//union find has two operations, find and union
//find displays what group an element belongs too
//union merges two groups together
//we use it to merge together paths that are similar