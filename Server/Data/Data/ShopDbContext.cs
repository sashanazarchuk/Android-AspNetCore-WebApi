using Data.Model;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Data
{
    public class ShopDbContext:IdentityDbContext
    {
        public ShopDbContext(DbContextOptions options):base(options){}
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            modelBuilder.Entity<Product>();
            modelBuilder.Entity<Categories>();
            modelBuilder.Entity<User>();
        }
        public DbSet<Product> Products { get; set; }
        public DbSet<Categories> Categories { get; set; }   
        public DbSet<User> User { get; set; }   
    }
}
