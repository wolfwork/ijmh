ijmh (It Just Might Happen)
====

name: ijmh<br />
description: "It Just Might Happen" is reality added based on events<br />
author: nsordk & PlugMania<br />
main: info.plugmania.ijmh.ijmh<br />
version: 2.4.0<br /><br />

<b>Latest Release</b><br />
v2.3.1 Bukkit: http://dev.bukkit.org/server-mods/ijmh/<br /><br />

<b>Dev. version</b><br />
v2.4.0 Github: https://github.com/PlugMania/ijmh/raw/master/Latest%20Build/ijmh.jar<br /><br />
              
<b>Changelog</b><br />
2.4.0<br />
General cleanup and recoding of deprecated classes<br />
2.3.2<br />
"Roses have thorns" now also works on Rose bushes too<br />
2.3.1<br />
Updated to CraftBukkit v1.7.9-R0.2 BETA<br />
2.3.0<br />
Updated to CraftBukkit v1.7.2-R0.3 BETA<br />
2.2.0<br />
Updated to CraftBukkit v1.6.2-R0.1 BETA<br />
2.1.x<br />
Started feature "Cactus Mania"<br />
v2.1.5<br />
Added Double Trouble to Crazy Combat feature<br />
v2.1.4<br />
Fixed Signs in Brew Explosion, and set signs:true as default.<br />
v2.1.3<br />
Fixed error with Cows Do Kick didn't create config options.<br />
Added options to add items or change armorpoints for Heavy Duty.<br />
Added permission (ijmh.immunity.tar) that prevents slow on player.<br />
Changed Struck by Lightning to check for non-user placed leaves.<br />
v2.1.2<br />
Added AboveSeaLevel as option in Struck By Lightning<br />
v2.1.1<br />
Fixed error from On Fire, where ItemInHand was null<br />
v2.1.0<br />
Fixed console-error when using SKULL_ITEM as HEAD, and BONES as FEET<br />
Changed Zombie Nation to spawn zombies on every deathcause or only zombies<br />
Added color code compability to languagefile<br />
Added Crazy Combat feature: Fair Play, Sparks, Backfire, Splinter, Bow Snaps, Weapon Breaks, Kill Rush<br />
Added Near Death feature<br />
v2.0.2<br />
Rework of the Heavy Duty update system, default values changed to: walkspeed 0.2, flyspeed 0.1, modifier 0.4<br />
Players in water will not be affected by Sneaky Pickup<br />
Fixed errorspam in console when attacked by entity<br />
v2.0.1<br />
Added reset option to walk- & flyspeeds<br />
v2.0.0<br />
Updated to CraftBukkit v1.4.5 R0.2<br />
Brand new way to manage/setup features<br />
All features split up for easier overview<br /> 
v1.4.1<br />
Updated to CraftBukkit v1.4.2 R0.1 for testing<br />
v1.4.0<br />
Preperation started towards building v2.0.0<br />
All features now merged into their own classes<br />
All features tested and merge was a success<br />
All feature commands moved to their class and translated to use same function for setup<br />
Changes to PlayerMoveEvent towards optimization<br />
Fixed bug in "Row your boat" where block was created higher than player<br />
Fixed commands to change Zombie Nation "whenzombie" and Brew Explosion "signs"<br /> 
v1.3.7<br />
#5 Fixed error on suicide command<br />
#5 Fixed error on inventoryCloseEvent with instanceof Player<br />
Fixed Creative mode movement being limited by protection points<br />
v1.3.6<br />
Added "Heavy Duty", Armor now reduce walk- and flyspeed based on protection points<br />
Added "Zombie Nation", a zombie has a chance to raise upon player getting killed<br />
v1.3.5<br />
Added "Sneaky Pickup", in order to pickup items, you will have to sneak.<br />
v1.3.4<br />
Added "World Drop", chance for items to fall out of the sky around players online.<br />
Partially added "Untamed Ride" and disabled it until actual riding plugin is decided or MC1.4 where pigs are controlable for riding<br />
v1.3.3<br />
Added "Unstable TNT", chance for TNT, when placed, to explode instantly and kill player.<br />
Fixed added messages from new version being null on the first load.<br />
Fixed damage on pressure plates that activates redstone.<br />
v1.3.2<br />
Added "BuggyBlock", check (100% default) for a range of blocktype to break underneath the player.<br /> 
v1.3.1<br />
Removed setting from Quicksand to restrict when raining (was bugged).<br />
v1.3.0<br />
Added "Dizzy In the Desert", chance during sunny daytime to get thirsty/dizzy. <br />
Added "Row your Boat", chance for boat to sink and player will fight not to drown<br />
Added Updatefunction for language.yml to use current language when updating with new version<br />
Added MultiWorld support, to disable worlds in each feature<br />
Added "Quicksand", chance to get sucked into the ground if not jumped enough<br />
Minor fixes and tweaks<br />
v1.2.2<br />
Added language.yml for editing the messages<br />
v1.2.1<br />
Added Metrics for usage statistics<br />
Added Material.PORK to Foodpoisoning as raw material<br />
Fixed Immunity permission for Tired Miner<br />
Fixed Catching Fire and the messagespam from putting it out<br />
v1.2.0<br />
Added "Fisherman on hook", chance to carch more items or catch mobs<br />
Added "Bump on the Rail", chance to get thrown out of the minecart<br />
Added chance to break Bow during PvP<br />
Added Tar, Sloweffect when walking over black wool<br />
v1.1.6<br />
Added compability for WorldGuard invincible flag<br />
v1.1.5<br />
Added chance for squid add POISON and BLINDNESS on being hit<br />
Added Brew explosion (Signs still bugged)<br />
Added compability with players inside a MazeMania game not to get struck by Lightning<br />
Added option for Lightning to skip choosen biomes<br />
Added walk on Red Roses causing slow and damage while moving through the roses.<br />
Disabled Redstone from triggering when In vehicle<br />
v1.1.4<br />
Added command for toggling ingame update messages.<br />
Added command interface to tweak all settings by command.<br />
Added chancemodifier to go lower than 1%<br />
Changed foodpoison to only trigger on raw food.<br />
Changed default duration for foodpoison to 5 seconds.<br />
Fixed bug where cure foodpoison triggered on different events.<br />
v1.1.3<br />
Added "The Happy Miner", Speeded or slowed when getting energized or tired from blockbreaking.<br />
Fixed falling into water giving concussion.<br />
v1.1.2<br />
Added Cows is supposed to be milked from the side and cows now kicks if milked from behind<br />
Effectstatements optimized for performance<br />
Fixed flight:on getting concussion from landing<br />
v1.1.1<br />
Unlucky crafter added "aka craftthumb"<br />
v1.1.0<br />
Versioncheck upon login or by command<br /> 
Removed debug in config.yml<br />
