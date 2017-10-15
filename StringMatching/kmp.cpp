#include <cstring>
#include <iostream>

// lpps[i]: longest proper prefix that is also a suffix of pat[0]...pat[i] 
int* calculate_lpps(char* pat){
	int pat_len = strlen(pat);
	int* lpps = new int[pat_len];

	lpps[0] = 0;
	int len = 0, i = 1;
	while(i < pat_len){
		if(pat[i] == pat[len]){
			len++;
			lpps[i] = len;
			i++;
		}
		else if(len == 0){
			lpps[i] = 0;
			i++;
		}
		else
			len = lpps[len - 1];	
	}

	return lpps;
}

void kmp_search(char* text, char* pattern){
	int len_text = strlen(text);
	int len_pattern = strlen(pattern);
	int *lpps = calculate_lpps(pattern);

	int i = 0, j = 0;
	while(i < len_text){
		if(text[i] == pattern[j]){
			i++;
			j++;
			if(j == len_pattern){
				std::cout << "pattern found in text at position i = " << i - len_pattern << std::endl;
				j = lpps[j - 1];
			}
		}
		else if(j == 0)
			i++;
		else
			j = lpps[j - 1];
	}

	delete[] lpps;
}


int main(){
	char txt[] = "ABABDABACDABABCABAB";
    char pat[] = "ABABCABAB";

    kmp_search(txt, pat);

	return 0;
}