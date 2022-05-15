import itertools

def listContains(closure, left_side):
  for i in left_side:
    if i not in closure:
      return False
  return True
def getClosure(R_attribute, F):
  closure_res = R_attribute.copy()
  saved = list()
  while(True):
    # danger
    if saved == closure_res:
      break
    saved = closure_res
    # dùng dict ở đây cx dc
    for depend_index in F:
      left_side = list(depend_index.split("-")[0].split('+'))
      right_side = list(depend_index.split("-")[1].split('+'))
      if listContains(closure_res, left_side):
        closure_res = closure_res + right_side
        result = [] 
        [result.append(x) for x in closure_res if x not in result] 
        closure_res = result
  return closure_res


def equal_ignore_order(a, b):
    unmatched = list(b)
    for element in a:
        try:
            unmatched.remove(element)
        except ValueError:
            return False
    return not unmatched
def findsubsets(s, n):
    return list(itertools.combinations(s, n))
def getKeys(R, F):
  Xi = list()
  for n in range(1, len(R) + 1):
    for j in findsubsets(R, n):
      Xi.append(list(j))
  # print(Xi)
  super_key = list()
  for i in Xi:
    closure_temp = getClosure(i, F)
    # print(i, closure_temp)
    if equal_ignore_order(closure_temp, R):
      super_key.append(i)
  super_key = sorted(super_key,key=lambda x : len(x), reverse=True)
  # print(super_key)
  res_temp = list()
  for i in range(len(super_key)):
    verification = True
    # print(super_key[i])
    for j in range(i+1, len(super_key)):
      # print("\t", super_key[j])
      # print(listContains(super_key[i],super_key[j]))
      if listContains(super_key[i],super_key[j]):
        verification = False
        break
    if verification:
      # print(super_key[i])
      res_temp.append(super_key[i])
  # print(res_temp)
  return res_temp

def readInputFile(links):
  with open(links) as file:
      input_list = [w[:-1] for w in file.readlines()]
  return input_list
def writeFile(links, data):
  with open(links, 'w') as writefile:
    for i in data:
      writefile.write(i)
    print('\t\tCOMPLETED')

# read file
if __name__ == "__main__":
  input_link = 'Input2.txt'
  input_list = readInputFile(input_link)
  output_link = 'Output2.txt'
  
  # User input
  X = ['SID', 'BirthYear']
  
  output_list = list()
  # file = open('/content/drive/MyDrive/Colab Notebooks/Output2.txt', "w")
  for i in input_list:
    R0 = i.split('_')[0]
    R = R0[R0.index('{')+1 : R0.index('}')].split(',')
    # print(R)
    F0 = i.split('_')[1]
    F = F0[F0.index('{')+1 : F0.index('}')].split(';')
    # print(type(F))
    keys = getKeys(R, F)
    if listContains(R, X):
      closure = getClosure(X, F)
    else:
      closure = []
    
    output_list.append(str(R0) + "\n" + str(F0) + "\nThe keys: " + str(keys) + "\nThe Closure of " + str(X) + ": " + str(closure) + "\r\n\n")  # for i in output_list:
    writeFile(output_link, output_list)


