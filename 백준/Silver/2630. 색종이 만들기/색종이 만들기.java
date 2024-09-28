import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] paper;
	static int white = 0, blue = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine()); // 색종이 크기
		paper = new int[N][N]; // 색종이

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// 0:하얀색, 1:파란색
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		foldPaper(0, 0, N);

		System.out.println(white);
		System.out.println(blue);
	}

	public static void foldPaper(int x, int y, int n) {
		int color = paper[y][x];
		boolean sameColor = true;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (paper[y + i][x + j] != color) {
					sameColor = false;
					i = N;
					break;
				}
			}
		}

		if (!sameColor) {
			foldPaper(x, y, n / 2);
			foldPaper(x + n / 2, y, n / 2);
			foldPaper(x, y + n / 2, n / 2);
			foldPaper(x + n / 2, y + n / 2, n / 2);
		} else if (color == 0) {
			white++;
		} else {
			blue++;
		}
	}
}