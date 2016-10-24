from itertools import product, imap
from operator import xor

Knight = True
Liar = False

def knl(b): return 'Knight' if b else 'Liar'

def test_conjecture(people, decls, conj):
	for d in decls:
		subj = d[0]
		subj_kl = conj[subj]
		
		for (obj, kl) in d[1:]:
			res = not xor(kl, subj_kl)
			if res != conj[obj]:
				print print_conjecture(people, conj) + ", violation: if %s is %s, statement %s -> %s contradicts conjecture %s -> %s" % (subj, knl(subj_kl), obj, knl(kl), obj, knl(conj[obj]))
				return False
	
	return True
	
def print_conjecture(people, conj):
	return "{ " + ", ".join(["%s -> %s" % (p, knl(conj[p])) for p in people]) + " }"

def solve(people, decls):
	combs = imap(lambda btuple: dict(zip(people, btuple)), product([True, False], repeat=len(people)))
	for conj in combs:
		if test_conjecture(people, decls, conj):
			print 'Solution found: ' + print_conjecture(people, conj)
			break
	else:
		print 'No valid solution'


# task definition
people = ['Arthur', 'Bernard', 'Victor']

decls = [
	['Arthur', ('Bernard', Knight)],
	['Bernard', ('Arthur', Liar), ('Victor', Liar)],
	['Victor', ('Victor', Knight)]
]

# solution
solve(people, decls)
