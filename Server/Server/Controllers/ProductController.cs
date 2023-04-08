using BusinessLogic.Interfaces;
using Data.Model;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Server.Models;

namespace Server.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductController : ControllerBase
    {
        private readonly IProductService productService;
        public ProductController(IProductService productService)
        {
            this.productService = productService;
        }

        [HttpGet("GetAll")]
        public IActionResult GetAll()
        {
            return Ok(productService.GetAll());
        }


        [HttpGet("get/{id}")]
        public IActionResult Get([FromRoute] int id)
        {
            var product = productService.GetById(id);

            if (product == null) return NotFound();

            return Ok(product);
        }


        [HttpPost]
        public IActionResult Create([FromBody] ProductDTO product)
        {
            productService.Create(product);

            return Ok();
        }
    }
}
