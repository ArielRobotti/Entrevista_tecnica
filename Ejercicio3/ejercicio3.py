from sys import argv

SEVEN_SEGMENTS = {	'0':{'vert':{'l':(1,1),'r':(1,1)}, 'horiz':(1,0,1)},	#vert[l] y vert[r] representan los segmentos (f y e), y (b y c) respectivamente
					'1':{'vert':{'l':(0,0),'r':(1,1)}, 'horiz':(0,0,0)},	#horiz representa los segmentos a, g y d de un display de siete segmentos standard
					'2':{'vert':{'l':(0,1),'r':(1,0)}, 'horiz':(1,1,1)},        
					'3':{'vert':{'l':(0,0),'r':(1,1)}, 'horiz':(1,1,1)},	#     _a_
					'4':{'vert':{'l':(1,0),'r':(1,1)}, 'horiz':(0,1,0)},	#  f |   | b
					'5':{'vert':{'l':(1,0),'r':(0,1)}, 'horiz':(1,1,1)},	#     _g_
					'6':{'vert':{'l':(1,0),'r':(1,1)}, 'horiz':(1,1,1)},	#  e |   | c
					'7':{'vert':{'l':(0,0),'r':(1,1)}, 'horiz':(1,0,0)},	#     _d_
					'8':{'vert':{'l':(1,1),'r':(1,1)}, 'horiz':(1,1,1)},
					'9':{'vert':{'l':(1,0),'r':(1,1)}, 'horiz':(1,1,1)}	   
				}
cache = {}
def is_mirrorable(hour):
	if hour in cache: 
		return cache[hour]
	if len(hour) == 2:
		left, rigth = SEVEN_SEGMENTS[hour[0]], SEVEN_SEGMENTS[hour[1]]
		cache[hour] = (left['vert']['l'], left['vert']['r'], left['horiz']) == (rigth['vert']['r'], rigth['vert']['l'], rigth['horiz'])
		return cache[hour]
	else:
		return is_mirrorable(hour[0]+hour[-1])	and is_mirrorable(hour[1]+hour[-2])

def test_exercise1(path):
	with open(path) as test_cases:
		for line in test_cases.readlines():
			if '#' in line:
				print(line.split()[-1], end = '. ')
			else:	
				if is_mirrorable(line.strip()):
					print('Se ve igual')
				else:
					print('No se ve igual')

if __name__ == '__main__':
	test_exercise1(argv[1]) if len(argv) > 1 else test_exercise1("ejercicio3-input.txt")