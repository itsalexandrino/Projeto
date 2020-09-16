import { Component, OnInit } from '@angular/core';
import { ProdutoModel } from '../model/Produto';
import { ProdutoService } from '../service/produto.service';
import { Router, ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { AlertasService } from '../service/alertas.service';

@Component({
  selector: 'app-delete-produto',
  templateUrl: './delete-produto.component.html',
  styleUrls: ['./delete-produto.component.css']
})
export class DeleteProdutoComponent implements OnInit {

  produto: ProdutoModel = new ProdutoModel()

  constructor(

    private produtoService: ProdutoService,
    private router: Router,
    private route: ActivatedRoute,
    private alert: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    let id: number = this.route.snapshot.params['id']
    this.findByIdProduto(id)

    if (environment.admin == false) {
      this.alert.showAlertDanger("SEM PERMISSÃO!!")
      this.router.navigate(["/home"])
    }

  }
  findByIdProduto(id: number) {
    this.produtoService.getByIdProduto(id).subscribe((resp: ProdutoModel) => {
      this.produto = resp
    })
  }
  btnSim() {
    this.produtoService.deleteProduto(this.produto.id).subscribe(() => {
      this.router.navigate(['/cadastrar-produto'])
      this.alert.showAlerSuccess('Produto apagado com sucesso!')
    })
  }
  btnNao() {
    this.router.navigate(['/cadastrar-categoria'])
  }
}
