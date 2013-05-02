import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;

public class WrappedResource implements Serializable {
	String name;
	private LinkedHashMap resources = new LinkedHashMap();
	
	public static String getFileName(String name) {
		return name + ".dat";
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void put(String key, Object value) {
		resources.put(key, value);
	}
	
	public Object get(String key) {
		return resources.get(key);
	}
	
	public boolean save(String dirName) throws IOException {
		File f = new File(dirName);
		if (!(f.exists() && f.isDirectory())) {
			return false;
		}
		File datFile = new File(dirName + "/" + getFileName(name));
		FileOutputStream datFos = new FileOutputStream(datFile);
		ObjectOutputStream datObjOs = new ObjectOutputStream(datFos);
		datObjOs.writeObject(this);
		datFos.close();
		return true;
	}

	public static WrappedResource restore(String fileName) throws IOException, ClassNotFoundException {
		File f = new File(fileName);
		if (!(f.exists() && f.isFile())) {
			return null;
		}
		File datFile = new File(fileName);
		FileInputStream datFis = new FileInputStream(datFile);
		ObjectInputStream datObjIs = new ObjectInputStream(datFis);
		Object o = datObjIs.readObject();
		datObjIs.close();
		return (WrappedResource)o;
	}

}
