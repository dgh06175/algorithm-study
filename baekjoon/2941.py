string = input()

alphaArr = ["c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="]

answer = 0

for v in alphaArr:
    #print(string.count(v))
    answer += string.count(v)
    string = string.replace(v, ".")
    #print(string)

answer += len(string)
answer -= string.count(".")

print(answer)