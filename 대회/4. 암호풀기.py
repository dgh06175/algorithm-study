decode_key = input()
code = input()

# 복호화 키와 암호가 주어진다. 암호는 대소문자와 공백
# 암호의 글자의 디코드 키에서의 순서를 구하고, 원래 알파벳의 순서를 적용하면 된다.

decode_data = ''

# 소문자를 넣으면 암호에서몇번째 소문자인지 리턴하는 함수
def alphabet_order(n):
  return ord(n) - ord('a')

for i in range(len(code)):
  if ord(code[i]) >= ord('a') and ord(code[i]) <= ord('z'):
    decode_data += decode_key[alphabet_order(code[i])]
  elif ord(code[i]) >= ord('A') and ord(code[i]) <= ord('Z'):
    decode_data += decode_key[alphabet_order(code[i].lower())].upper()
  elif code[i] == ' ':
    decode_data += ' '
  

print(decode_data)