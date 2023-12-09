package icu.trub.aoc.day05

class Almanac(val seeds: List<Long>, val maps: List<AlmanacMap>) {
    val locations: List<Long>
        get() {
            val result = seeds.map { seed ->
                var mapped = seed
                for (map in maps) {
                    mapped = map.get(mapped)
                }
                mapped
            }
            return result
        }

    companion object {
        fun parse(input: List<String>): Almanac {
            val seeds = parseSeeds(input.first())
            val maps = parseMaps(input.subList(1, input.size).filter { l -> l.isNotBlank() })
            return Almanac(seeds, maps)
        }

        internal fun parseSeeds(seedsLine: String) = seedsLine.split(':').last().trim().split(' ').map(String::toLong)

        private fun parseMaps(mapsLines: List<String>): List<AlmanacMap> = buildList {
            var singleMapRangesDescriptors: MutableList<AlmanacMap.RangeDescriptor> = mutableListOf()
            for (line in mapsLines) {
                if (line.contains("[0-9]".toRegex())) {
                    singleMapRangesDescriptors.add(AlmanacMap.RangeDescriptor.parse(line))
                } else {
                    // store what was collected so far
                    if (singleMapRangesDescriptors.isNotEmpty()) {
                        add(AlmanacMap(singleMapRangesDescriptors.toList()))
                    }
                    // prepare for storing new
                    singleMapRangesDescriptors = mutableListOf()
                }
            }
            // last line
            if (singleMapRangesDescriptors.isNotEmpty()) {
                add(AlmanacMap(singleMapRangesDescriptors.toList()))
            }
        }

    }

}
