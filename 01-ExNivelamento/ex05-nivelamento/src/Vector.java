import java.util.Random;

class Vector {

	private int len;
	private int vet[];

	Vector() {
		setLen(6);
		int v[] = { 1, 3, 5, 7, 9, 11 };
		setVet(v);
	}

	Vector(int tam) {
		setLen(tam);
		int v[] = new int[tam];
		Random gen = new Random();

		for (int i = 0; i < len; i++) {
			v[i] = gen.nextInt(31);
		}

		setVet(v);
	}
	
	Vector(int[]vector){
		setVet(vector);
		setLen(vector.length);
	}
	
	Vector(String[]vector){
		setLen(vector.length);
		int tempVet[] = new int[vector.length];
		for(int i = 0; i<vector.length;i++) {
			tempVet[i] = Integer.parseInt(vector[i]);
		}
		
		setVet(tempVet);
	}

	public void printVector() {
		int len = getLen();
		//System.out.print("Vetor: ");
		for (int i = 0; i < len; i++) {
			System.out.print(" " + vet[i]);
		}
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int[] getVet() {
		return vet;
	}

	public void setVet(int vet[]) {
		this.vet = vet;
	}

}
