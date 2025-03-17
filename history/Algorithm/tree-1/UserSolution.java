import java.util.*;

class UserSolution {
	private Folder root;
	
	class Folder {
		String name;
		Folder parentFolder;
		Map<String, Folder> subFolders;
		
		Folder(String name, Folder parentFolder) {
			this.name = name;
			subFolders = new HashMap<>();
			this.parentFolder = parentFolder;
		}
		
		void makeFolder(String name) {
			subFolders.put(name, new Folder(name, this));
		}
		
		void deleteSubFolderByName(String name) {
		    if (subFolders.containsKey(name)) {
		        subFolders.remove(name);
		    }
		}

		void deleteFromParent() {
			parentFolder.deleteSubFolderByName(name);
		}
	}
	
	// O(1), n < 50,000 -> O(NLogN) 까지
	void init(int n) {
		root = new Folder("/", null);
	}
	
	// 디렉터리 생성 O(1)
	void cmd_mkdir(char[] path, char[] name) {
	    String[] pathAry = pathToStrAry(path);
	    Folder destFolder = getDestFolder(pathAry);
		String strName = String.valueOf(name).trim();
		destFolder.makeFolder(strName);
//	    printFolder(root, 0);
	}
	
	// 디렉터리 삭제 O(H) (메모리 해제 안하고 삭제하면)
	void cmd_rm(char[] path) {
	    String[] pathAry = pathToStrAry(path);
	    Folder destFolder = getDestFolder(pathAry);

//	    System.out.println(destFolder.name + "을 삭제합니다.");
	    destFolder.deleteFromParent();
//	    printFolder(root, 0);
	}
	
	// 디렉터리 복사 O(N)
	void cmd_cp(char[] srcPath, char[] dstPath) {
		String[] srcPathAry = pathToStrAry(srcPath);
		String[] dstPathAry = pathToStrAry(dstPath);
		
		Folder srcFolder = getDestFolder(srcPathAry);
		Folder dstFolder = getDestFolder(dstPathAry);
		
		cpFolders(dstFolder, srcFolder);
//	    printFolder(root, 0);
	}
	
	// dst 폴더에 src 폴더와 하위 디렉토리를 복사한다.
	private void cpFolders(Folder dst, Folder src) {
		dst.makeFolder(src.name);
		Map<String, Folder> subs = src.subFolders;
		for(Map.Entry<String, Folder> sub: subs.entrySet()) {
			cpFolders(dst.subFolders.get(src.name), sub.getValue());
		}
	}
	
	// 디렉터리 이동 O(1)
	void cmd_mv(char[] srcPath, char[] dstPath) {
		String[] srcPathAry = pathToStrAry(srcPath);
		String[] dstPathAry = pathToStrAry(dstPath);
		
		Folder srcFolder = getDestFolder(srcPathAry);
		Folder dstFolder = getDestFolder(dstPathAry);
		
		srcFolder.deleteFromParent();
		srcFolder.parentFolder = dstFolder;
		dstFolder.subFolders.put(srcFolder.name, srcFolder);
//		printFolder(root, 0);
	}
	
	// 하위 디렉터리 개수 확인 O(1)
	int cmd_find(char[] path) {
		String[] pathAry = pathToStrAry(path);
		Stack<Folder> stack = new Stack<>();
		stack.add(getDestFolder(pathAry));

		int count = 0;
		while (!stack.isEmpty()) {
			Folder folder = stack.pop();
			for(Folder sub: folder.subFolders.values()) {
				stack.push(sub);	
				count++;
			}
		}
//		System.out.println(count);
		return count;
	}
	
	private String[] pathToStrAry(char[] path) {
		String strPath = String.valueOf(path).trim();
		if (strPath.startsWith("/")) {
	        strPath = strPath.substring(1);
	    }
	    if (strPath.endsWith("/")) {
	        strPath = strPath.substring(0, strPath.length() - 1);
	    }
	    return Arrays.stream(strPath.split("/"))
	    		.map(a -> a.trim())
	    		.toArray(String[]::new);
	}
	
	private Folder getDestFolder(String[] pathAry) {
		Folder subFolder = root;
	    for (String pathName: pathAry) {
	    	if (!subFolder.subFolders.containsKey(pathName)) {
	    		break;
	    	}
	    	subFolder = subFolder.subFolders.get(pathName);
	    }
	    return subFolder;
	}
	
//	private void printFolder(Folder folder, int depth) {
//	    String indent = new String(new char[depth]).replace("\0", "  ");
//	    System.out.println(indent + folder.name);
//
//	    List<String> subFolderNames = new ArrayList<>(folder.subFolders.keySet());
//	    Collections.sort(subFolderNames);
//
//	    for (String name : subFolderNames) {
//	        printFolder(folder.subFolders.get(name), depth + 1);
//	    }
//	}
}
