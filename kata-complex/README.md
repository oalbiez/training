# Pricer un panier


1. Prix total simple

Etant donné un panier composé de produits, calculer son prix total.
Un produit est composé d'un identifiant, d'un label et de son prix en euros.
Le panier est un ensemble de produit.


2. Promotion X achetés, Y offerts

Je veux pouvoir proposer des offres promotionelles. Mon offre la plus courante est du style "pour 3 produits X achetés, 1 offert".

3. Promotion 20% sur cette liste de produits
4. Promotion 5€ de réductions sur ce produit
5. Frais de livraisons

Je veux associer des frais de livraisons en fonction du prix total du panier.
Par exemple:
- entre 0 et 1000€: 8€ de frais
- entre 1000€ et 3000€: 4€ de frais
- au dela 0€ de frais

6. J'ai besoin de spécifier le TVA en fonction du produit
7. J'ai besoin de gérer des francs suisses, avec un taux de converssion
8. J'ai besoin de gérer des prix en dollars
9. J'ai besoin de gérer la TVA en fonction du pays/état
10. J'ai besoin de supporter le bitcoin (et donc des mantants très petits et une précision en fonction de la monaie)
11. J'ai besoin de gérer le poids des paniers pour calculer les frais de transport
12. J'ai besoin de gérer le volume des panier pour calculer les frais de transport
13. Je veux limiter le poids max et volume max d'un panier
14. Je veux générer une facture PDF du panier
15. Je veux un détail des offres promotionelles dans la facture
16. Je veux identifier le client pour lui faire une remise permanante sur des catégories de produits (service aux proffessionels)
17.  
