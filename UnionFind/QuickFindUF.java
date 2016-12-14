public class QuickFindUF{
    private int [] id;

    public QuickFindUF(int N){
        id = new int[N];
        for(int i = 0; i < N; i++)
            id[i] = i;
    }

    public void union(int a, int b){
        if(!connected(a, b)){
            int id_a = id[a];
            for(int i = 0; i < id.length; i++)
                if(id[i] == id_a)
                    id[i] = id[b];
        }
    }

    public boolean connected(int a, int b){
        return id[a] == id[b];
    }
}