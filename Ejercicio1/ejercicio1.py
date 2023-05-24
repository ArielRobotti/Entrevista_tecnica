from sys import argv

DIRECTIONS = [(0, 1), (0, -1), (1, 0), (-1, 0)]
class Place:
    def __init__(self,row,col):
        self.row = row
        self.column = col

class Stain:
	def __init__(self,color=None,size=0):
		self.color = color
		self.size = size
	def __str__(self):
		return str((self.color,self.size))

def is_in_board(board,row,col):
	return row in range(len(board)) and col in range(len(board[0]))

def visit_neighbors(board,visited, same_color):
	result = []
	color = board[same_color[0].row][same_color[0].column]
	for place in same_color:
		visited[place.row][place.column] = True
		for row_n, col_n in [(place.row + d[0], place.column + d[1]) for d in DIRECTIONS]:
			if is_in_board(board,row_n, col_n) and not visited[row_n][col_n] and board[row_n][col_n] == color:
				result.append(Place(row_n,col_n))
				visited[row_n][col_n] = True
	return result

def scan(board,visited,place):
    size = 1
    same_color = [place]
    while same_color: 
        same_color = visit_neighbors(board,visited,same_color)
        size += len(same_color) 
    return size

def get_big_stain(board):
    rows = range(len(board))
    cols = range(len(board[0]))
    visited = [[False for i in  cols] for j in  rows]
    big_stain = Stain()
    for row in rows:
        for col in cols:
            #if (row+col) % 2 == 0: #Optimización: Recorrer todos los casilleros del tablero resulta redundante
            			 			#en principio se pueden reducir las iteraciones a la mitad si dejamos 'agujeros' de 1  
            						#espacio sin iterar en el tablero y a medida que las manchas encontradas crecen en tamaño,
            						#también crezca el tamaño de esos agujeros. El recorrido resultante de la iteración sería una
            						#especie de malla que "deja pasar" todo lo que sea mas chico que la mancha mas grande que se
            						#encontró hasta el momento
            if not visited[row][col]:
                size = scan(board,visited,Place(row,col))
                if size > big_stain.size:
                    big_stain = Stain(board[row][col], size)                
    return big_stain

def test_exercise1(path):
	with open(path) as test_cases:
		raw_board = []
		for line in test_cases.readlines():
			if '#' in line:
				if raw_board != []:
					print(get_big_stain(raw_board))
					raw_board = []
				print(line.split()[-1], end = '. ')
			else:
				raw_board.append(line.strip())
		print(get_big_stain(raw_board))

def main():
	test_exercise1(argv[1]) if len(argv) > 1 else test_exercise1("ejercicio1-input.txt")
			
if __name__ == '__main__':
	main()
