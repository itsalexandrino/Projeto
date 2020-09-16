import { CategoriaModel } from './Categoria';

export class ProdutoModel {
    public id: number;
    public nome: string;
    public preco: number;
    public produtoImagem: string;
    public descricao: string;
    public material: string;
    public cor: string;
    public quantidade: number;
    public disponibilidade: boolean;
    public categoria :CategoriaModel;
}