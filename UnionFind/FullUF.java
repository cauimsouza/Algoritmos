public class FullUF{
	private int[] id;
	private int[] rank;

	public FullUF(int N){
		id = new int[N];
		rank = new int[N];
		for(int i = 0; i < N; i++){
			id[i] = i;
			rank[i] = 0;
		}
	}

	private int root(int i){
		if(i == id[i])
			return i;
		return id[i] = root(id[i]); // path compression
	}

	public boolean connected(int p, int q){
		return root(p) == root(q);
	}

	public void union(int p, int q){
		int i = root(p);
		int j = root(q);
		if(i == j) return;
		// union by rank
		if(rank[i] == rank[j]){
			id[j] = i;
			rank[i]++;
		}
		else{
			if(rank[i] > rank[j]) id[j] = i;
			else id[i] = j;
		}
	}
}