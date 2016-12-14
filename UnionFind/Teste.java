class Teste{
    public static void main(String[] args){
        QuickFindUF qf = new QuickFindUF(10);
        qf.union(1,5);
        qf.union(2,7);
        qf.union(6,8);
        qf.union(1,9);
        if(qf.connected(5,9))
            System.out.println("Sim!!!");
        if(!qf.connected(5,6))
            System.out.println("Não!!!");
    }
}