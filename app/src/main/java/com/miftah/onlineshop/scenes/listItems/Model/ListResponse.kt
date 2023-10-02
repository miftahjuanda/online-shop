package com.miftah.onlineshop.scenes.listItems.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ListResponse(
    val status: Int,
    val error: String?,
    val data: List<DatumProduct>,
)

@Parcelize
data class DatumProduct(
    @SerializedName("umkm_id")
    val umkmid: Int,
    @SerializedName("umkm_name")
    val umkmName: String,
    @SerializedName("umkm_contact")
    val umkmContact: String,
    @SerializedName("umkm_account_number")
    val umkmAccountNumber: String,
    @SerializedName("umkm_account_bank")
    val umkmAccountBank: String,
    @SerializedName("umkm_account_bank_nama")
    val umkmAccountBankName: String,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_description")
    val productDescription: String,
    @SerializedName("product_stock")
    val productStock: Int,
    @SerializedName("product_price")
    val productPrice: String,
    @SerializedName("product_unit")
    val productUnit: String,
    @SerializedName("product_category")
    val productCategory: String,
    @SerializedName("product_images")
    val productImages: List<ProductImage>,
) : Parcelable

@Parcelize
data class ProductImage(
    @SerializedName("image_path")
    val imagePath: String,
) : Parcelable
