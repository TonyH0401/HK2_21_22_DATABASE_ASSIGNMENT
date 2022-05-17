def readInputFile(links):
  with open(links) as file:
      input_list = [w[:-1] for w in file.readlines()]
  return input_list
def writeFile(links, data):
  with open(links, 'w') as writefile:
    for key, value in data.items():
      output_str = str(key) + " : " + str(value) + '\n'
      writefile.write(output_str)
    print("\t\tCOMPLETED")


def getE(entity):
  # get entity name
  entity_name = entity[: entity.index('(')]
  # get all the attribute of the entity
  temp = entity[entity.index('(') + 1 : entity.index(')')].split(',')
  # get attribute that are not primary key
  attribute = list()
  [attribute.append(x) for x in temp if '[' not in x]
  # get the primary keys
  temp_PK_str = temp[0][1:-1]
  primary_key = temp_PK_str.split(',')
  # concat the primary key then other attributes
  total_att = [primary_key]
  total_att = total_att + attribute
  return [entity_name, total_att]
def getR(relationship):
  # get relationship's name
  relationship_name = relationship[0 : relationship.index('(')]
  temp = relationship[relationship.index('(')+1 : relationship.index(')')].split(',')
  # get relationship type
  relationship_type = temp[0]
  # get relationship entities
  relationship_entities = temp[1].split('-')
  # get relationship attributes
  relationship_att = temp[2][temp[2].index('[')+1 : temp[2].index(']')].split(',')
  # concat
  total_rela = [relationship_name]
  total_rela.append(relationship_type)
  total_rela.append(relationship_entities)
  total_rela.append(relationship_att)
  return total_rela
def divide_E_R(raw_list):
  E_dict = dict()
  R_list = list()
  #create E_dict and R_list 
  for i in raw_list:
    if i[0] is 'E':
      temp = getE(i[2:])
      E_dict[temp[0]] = temp[1]
    elif i[0] is 'R':
      temp = getR(i[2:])
      # print(temp)
      R_list.append(temp)
  return [E_dict, R_list]


def one_one_relation(name1, entity1, name2, entity2, relationship_att):
  result_dict = dict()
  # always add to the second one
  # add relationship attribute
  entity2_temp = entity2 + relationship_att
  # put PK in entity1 to entity2
  entity1_PK = [i for i in entity1 if isinstance(i, list)][0]
  entity2_temp = entity2_temp + entity1_PK
  result_dict[name1] = entity1
  if '' in entity2_temp:
    entity2_temp.remove('') # this is only if the relationship attribute is null
  result_dict[name2] = entity2_temp
  return result_dict
def one_many_relation(name1, entity1, name2, entity2, relationship_att):
  result_dict = dict()
  # entity1 is 1 and entity2 is N, we add PK 1 to N
  # add relationship attribute
  entity2_temp = entity2 + relationship_att
  # get entity1 PK
  entity1_PK = [i for i in entity1 if isinstance(i, list)][0]
  # add PK from entity 1 to entity N
  entity2_temp = entity2_temp + entity1_PK
  # add to dictionary
  result_dict[name1] = entity1
  if '' in entity2_temp:
    entity2_temp.remove('') # this is only if the relationship attribute is null
  result_dict[name2] = entity2_temp
  return result_dict
def many_many_relation(entity1, entity2, relationship_att, relationship_name):
  result_dict = dict()
  result_list = list()
  # get enity1 PK
  entity1_PK = [i for i in entity1 if isinstance(i, list)][0]
  # get enity2 PK
  entity2_PK = [i for i in entity2 if isinstance(i, list)][0]
  # new entity PK
  new_entity_PK = entity1_PK + entity2_PK
  # add new_entity_PK to list then relationship_att
  result_list.append(new_entity_PK)
  result_list = result_list + relationship_att
  if '' in result_list:
    result_list.remove('') # this is only if the relationship attribute is null
  # add to a dictionary
  result_dict[relationship_name] = result_list
  return result_dict

  
def master_function(E, R):
  # for i in R:
  #   print(i)
  # for key, value in E.items():
  #   print(key, value)
  # we work from 1-1 to 1-N to N-N, there might be a sort function for these types
  E_dict_copy = E.copy()
  for i in R:
    if i[1] == '1-1':
      temp = list()
      for j in i[2]:
        temp.append(j)
        temp.append(E_dict_copy.get(j))
      one_one_res = one_one_relation(temp[0], temp[1], temp[2], temp[3], i[3])
      E_dict_copy.update(one_one_res)
    elif i[1] == '1-N':
      temp = list()
      for j in i[2]:
        temp.append(j)
        temp.append(E_dict_copy.get(j))
      one_many_res = one_many_relation(temp[0], temp[1], temp[2], temp[3], i[3])
      E_dict_copy.update(one_many_res)
    elif i[1] == 'N-N':
      temp = list()
      for j in i[2]:
        temp.append(E_dict_copy.get(j))
      many_many_res = many_many_relation(temp[0], temp[1], i[3], i[0])
      E_dict_copy.update(many_many_res)
  # for key, value in E_dict_copy.items():
  #   print(key, value)
  return E_dict_copy


if __name__ == "__main__":
  input_link = readInputFile('Input1.txt')
  
  raw_list = input_link.copy()
  temp = divide_E_R(raw_list)

  data = master_function(temp[0], temp[1])

  output_link = ('Output1.txt')
  writeFile(output_link, data)
