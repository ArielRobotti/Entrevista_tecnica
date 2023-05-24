from sys import argv

to_15 = ['cero','uno','dos','tres','cuatro','cinco','seis','siete','ocho','nueve','diez','once','doce','trece','catorce','quince']
to_30 = to_15 + ['dieci'+ to_15[x] for x in range(6,10)] + ['veinte'] + ['veinti'+ to_15[x] for x in range(1,10)]
tens_to_100 = ['','','','treinta', 'cuarenta', 'cincuenta', 'sesenta', 'setenta','ochenta','noventa','cien']
hundreds = ['','ciento','doscientos','trescientos','cuatrocientos','quinientos','seiscientos','setecientos','ochocientos','novecientos']

def number_to_text(number):
	number = int(number)
	if number < 1000:
		if number == 100:  
			return 'cien'
		if number in range(30): 
			return to_30[number]
		if number % 100 < 30 :
			tens = to_30[number%100] * (number%100 != 0)
			units = ''
		else:
			tens = tens_to_100[(number % 100)//10] + ' y' * (number%10 != 0)
			units = to_15[number % 10] * ((number % 10) != 0)		
		return f'{hundreds[number // 100]} {tens} {units}'.strip()

	else:	
		remove_one = (number // 1000) != 1
		remove_zero = (number % 1000) != 0
		remove_o = -1 if ((number//1000) % 10 == 1) and ((number//1000 % 100)!=11) else None							
		left_side = number_to_text(number // 1000)[:remove_o] * remove_one
		rigth_side = number_to_text(number % 1000) * remove_zero
		return f'{left_side} mil {rigth_side}'.strip()

def test_exercise2(path):
	with open(path) as test_cases:
			for line in test_cases.readlines():
				if '#' in line:
					print(line.split()[-1], end = '. ' )
				else:
					print(number_to_text(line))

if __name__ == '__main__':
	test_exercise2(argv[1]) if len(argv) > 1 else test_exercise2("ejercicio2-input.txt")
