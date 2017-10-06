import java.util.*;

class Trie{
	TrieNode root;

	Trie(){
		root = new TrieNode();
	}

	// add a new word to the trie
	void addWord(String s){
		TrieNode node = root;
		for(int i = 0; i < s.length(); i++){
			int id = Character.getNumericValue(s.charAt(i)) - Character.getNumericValue('a'); 
			if(node.edges.get(id) == null){
				TrieNode newNode = new TrieNode();
				node.edges.set(id, newNode);
				node = newNode;
			}
			else
				node = node.edges.get(id);
			node.prefixes++;
		}
		node.words++;
	}

	// return the number of strings with prefix s inserted in the trie
	int countPrefixes(String s){
		TrieNode node = root;
		for(int i = 0; i < s.length(); i++){
			int id = Character.getNumericValue(s.charAt(i)) - Character.getNumericValue('a');
			if(node.edges.get(id) == null)	return 0;
			node = node.edges.get(id);
		}
		return node.prefixes;
	}

	// return the number of strings s inserted in the trie
	int countWords(String s){
		TrieNode node = root;
		for(int i = 0; i < s.length(); i++){
			int id = Character.getNumericValue(s.charAt(i)) - Character.getNumericValue('a');
			if(node.edges.get(id) == null)	return 0;
			node = node.edges.get(id);
		}
		return node.words;
	}  

	// return true iff the trie contains the string s
	boolean contains(String s){
		return countWords(s) > 0;
	}

	class TrieNode{
		int words;
		int prefixes;
		ArrayList<TrieNode> edges;

		TrieNode(){
			words = 0;
			prefixes = 0;
			edges = new ArrayList<>(Collections.nCopies(26, null));
		}
	}

	public static void main(String[] args){
		Trie trie = new Trie();
		trie.addWord("cauim");
		trie.addWord("mae");
		trie.addWord("irma");
		trie.addWord("tio");
		trie.addWord("cauim");
		trie.addWord("maemadruga");
		
		System.out.println(trie.contains("irm"));
		System.out.println(trie.countWords("cauim"));
		System.out.println(trie.countPrefixes("maem"));
	}
}