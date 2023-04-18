
public class HandleVector {

	public static int[] sumVector(int[] v) {

		int[] vSumed;
		int count = 0;
		if (v.length % 2 == 0) {
			
			vSumed = new int[v.length / 2];
			int tam = v.length / 2;
			
			for (int i = 0; i < tam; i++) {
				vSumed[i] = v[count] + v[count + 1];
				count += 2;
			}
		} else {
			
			vSumed = new int[(v.length / 2) + 1];
			int tam = (v.length / 2)+1;
			
			for (int i = 0; i < tam; i++) {
				if (i == tam-1) {
					vSumed[i] = v[count] + v[count];
					count += 2;
				} else {
					vSumed[i] = v[count] + v[count + 1];
					count += 2;
				}
			}
		}

		return vSumed;

	}
}
